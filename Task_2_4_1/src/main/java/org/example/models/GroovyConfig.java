package org.example.models;

import groovy.lang.*;
import groovy.util.DelegatingScript;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.example.Main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collection;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper=false)
@Data
public class GroovyConfig extends GroovyObjectSupport {
    private URI scriptPath;

    @SneakyThrows
    public void methodMissing(String name, Object args) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(name);
        if (metaProperty != null) {
            Closure<?> closure = (Closure<?>) ((Object[]) args)[0];
            Class<?> type = metaProperty.getType();
            Constructor<?> constructor = type.getConstructor();
            Object value = getProperty(name) == null
                    ? constructor.newInstance() :
                    getProperty(name);
            closure.setDelegate(value);
            closure.setResolveStrategy(Closure.DELEGATE_FIRST);
            closure.call();
            setProperty(name, value);
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    @SneakyThrows
    public void include(String path) {
        URI uri = Paths.get(scriptPath).getParent().resolve(path).toUri();
        runFrom(uri);
        postProcess();
    }

    @SneakyThrows
    public void runFrom(URI uri) {
        this.scriptPath = uri;
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript) sh.parse(uri);
        script.setDelegate(this);
        script.run();
    }

    @SneakyThrows
    public void postProcess() {
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            Object value = getProperty(metaProperty.getName());
            if (Collection.class.isAssignableFrom(metaProperty.getType())
                    && value instanceof Collection) {
                ParameterizedType collectionType = (ParameterizedType) getClass()
                        .getDeclaredField(metaProperty.getName()).getGenericType();
                Class itemClass = (Class) collectionType.getActualTypeArguments()[0];
                if (GroovyConfig.class.isAssignableFrom(itemClass)) {
                    Collection collection = (Collection) value;
                    Collection newValue = collection.getClass()
                            .getConstructor().newInstance();
                    for (Object o : collection) {
                        if (o instanceof Closure) {
                            Object item = itemClass.getConstructor().newInstance();
                            ((GroovyConfig) item).setProperty("scriptPath", scriptPath);
                            ((Closure<?>) o).setDelegate(item);
                            ((Closure<?>) o).setResolveStrategy(Closure.DELEGATE_FIRST);
                            ((Closure<?>) o).call();
                            ((GroovyConfig) item).postProcess();
                            newValue.add(item);
                        } else {
                            newValue.add(o);
                        }
                    }
                    setProperty(metaProperty.getName(), newValue);
                }
            }
        }
    }
}
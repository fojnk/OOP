package org.example.Operations;

/**
 * абстрактный метод для описания структуры операции.
 */
abstract public class Operation {
    protected Double arg1;
    protected Double arg2;

    /**
     * абстрактный метод для выполнения операции.
     *
     * @return - значение функции
     */
    public abstract Double evaluate();
}
package org.example.operations;

/**
 * абстрактный метод для описания структуры операции.
 */
public abstract class Operation {
    protected Double arg1;
    protected Double arg2;

    /**
     * абстрактный метод для выполнения операции.
     *
     * @return - значение функции
     */
    public abstract Double evaluate();
}
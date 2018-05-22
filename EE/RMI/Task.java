package RMI;

import java.io.Serializable;

public class Task implements Serializable
{
    public double a;
    public double b;
    final char f = '+';

    public Task(double a, double b)
    {
        this.a = a;
        this.b = b;
    }
}

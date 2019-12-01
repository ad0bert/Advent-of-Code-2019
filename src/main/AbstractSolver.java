package main;

public abstract class AbstractSolver implements AdventSolver {

    protected final String inputFile1;
    protected final String inputFile2;

    public AbstractSolver(String day) {
        this.inputFile1 = "./resources/day" + day + "/input1.txt";
        this.inputFile2 = "./resources/day" + day + "/input2.txt";
    }

    @Override
    public abstract void solvePart1();

    @Override
    public abstract void solvePart2();
}

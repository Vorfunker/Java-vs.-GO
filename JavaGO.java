abstract class Feline {
    int size;
    double danger;
    
    public boolean isDangerous(){
        if (this.danger > 5) {
            return true;
        }
        return false;
    }
    
    public abstract void makeNoise();
    
    public double cuteness() {
        return 100 / (double)this.size - this.danger;
    }
    
    public Feline(int size, double danger){
        this.size = size;
        this.danger = danger;
    }
}

class Lion extends Feline {
    @Override
    public void makeNoise(){
        System.out.println("Raaawrr");
    }
    
    public Lion (int size, double danger){
        super(size, danger);
    }
}

class Cat extends Feline {
    @Override
    public void makeNoise(){
        System.out.println("Miau");
    }
    
    public Cat (int size, double danger){
        super(size, danger);
    }
}

public class Main
{
	public static void main(String[] args) {
		Lion l1 = new Lion(30, 10);
		Cat c1 = new Cat(10, 2);
		System.out.println("Cuteness Lion: " + l1.cuteness());
		System.out.println("Cuteness Cat: " + c1.cuteness());
		l1.makeNoise();
		c1.makeNoise();
		System.out.println("Is the Lion dangerous? -> " + l1.isDangerous());
		System.out.println("Is the Cat dangerous? -> " + c1.isDangerous());
	}
}

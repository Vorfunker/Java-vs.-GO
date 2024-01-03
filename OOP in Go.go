package main

import "fmt"

type Animal interface{
    isDangerous()
    makeNoise()
    cuteness() float32
}

type Feline struct{
    size int
    danger float32
}

func (f *Feline) isDangerous() bool{
    if (f.danger > 5){
        return true
    }
    return false
}

func (f *Feline) cuteness() float32{
    return 100 / float32(f.size) - f.danger
}

type Lion struct{
    Feline
}

func (l *Lion) makeNoise() {
    println("Raaawrr")
}

type Cat struct{
    Feline
}

func (c *Cat) makeNoise() {
    println("Miau")
}

func main() {
    l1 := Lion{Feline{30,10}}
    c1 := Cat{Feline{10,2}}
    fmt.Println("cuteness Lion: ", l1.cuteness())
    fmt.Println("Cuteness Cat: ", c1.cuteness())
	l1.makeNoise()
	c1.makeNoise()
	fmt.Println("Is the Lion dangerous? -> ", l1.isDangerous())
	fmt.Println("Is the Cat dangerous? -> ", c1.isDangerous())
}









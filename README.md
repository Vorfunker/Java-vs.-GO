# OOP Java-vs.-GO

## Intro
Java und Go sind zwei vielseitige Programmiersprachen mit vielen Gemeinsamkeiten.
Während Java zu den älteren und etablierteren Programmiersprachen zählt, die die
objektorientierte Programmierung maßgeblich geprägt hat und umfassende Prinzipien und
Funktionen zur Anwendungsentwicklung bietet, zeichnet sich Go durch seine Einfachheit
und Effizienz aus. Die moderne Sprache wurde entwickelt, um die Komplexität
herkömmlicher Sprachen zu reduzieren, gleichzeitig aber eine hohe Leistung zu bieten.

Obwohl Go nicht die traditionellen OOP-Strukturen von Java übernimmt, bietet es dennoch
Mechanismen zur Implementierung von objektorientierten Konzepten. Es folgt ein
Vergleich zwischen den Prinzipien in Java und in Go.

## Nominales und Strukturelles Subtyping
Nominales Subtyping und Strukturelles Subtyping sind zwei verschiedene Ansätze zur
Typisierung in Programmiersprachen.

### Java (Nominal Subtyping)

Java verwendet nominale Subtypisierung, was bedeutet, dass die Frage, ob ein Typ ein Untertyp eines anderen ist, auf den Namen der Typen und expliziten Deklarationen im Code basiert. Zum Beispiel ist eine Klasse eine Unterklasse einer anderen Klasse, wenn sie mit dem Schlüsselwort `extends` deklariert wird. Ein Interface wird von einer Klasse implementiert, wenn die Klasse es mit dem Schlüsselwort `implements` deklariert. Dies gilt auch dann, wenn es zwei Klassen oder Interfaces mit identischen Elementen, aber unterschiedlichen Namen gibt; sie gelten als verschiedene Typen.

### Go (Structural Subtyping)

In Go wird strukturelle Subtypisierung verwendet, speziell für Interfaces. In Go wird ein Typ als die Schnittstelle erfüllend betrachtet, wenn er alle Methoden enthält, die in dieser Schnittstelle definiert sind, selbst wenn dies nicht explizit erklärt wird. Dies wird ausschließlich anhand der Struktur des Typs bestimmt - wenn er die erforderlichen Methoden hat, erfüllt er die Schnittstelle. Es ist nicht notwendig, explizit anzugeben, dass ein Typ eine Schnittstelle implementiert. Dies ermöglicht eine Art von "Duck-Typing", bei dem der Typ einer Variablen weniger wichtig ist als das Verhalten, das er unterstützt.

### Beispiele

Die folgenden beiden Beispiele erläutern die Unterschiede der beiden Strategien

```java
interface Shape {
    double getArea();
}

class Rectangle {
    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double getArea() {
        return length * width;
    }
}

public static void main(String[] args) {
    Shape s = new Rectangle(5, 6);  // This will throw a compile-time error due to the constraints of nominal subtyping
}
```

```go
type Shape interface {
    Area() float64
}

type Rectangle struct {
    Length, Width float64
}

func (r Rectangle) Area() float64 {
    return r.Length * r.Width
}

func main() {
    var s Shape
    s = Rectangle{5, 6} // This will work in Go due to structural subtyping
    fmt.Println(s.Area())
}
```

## Vererbung

### Java
In Java wird Vererbung durch das Schlüsselwort `extends` ausgedrückt und
Klassenhierarchien kommen zum Einsatz. Eine Subklasse kann Eigenschaften und
Methoden von einer Superklasse bzw. Basisklasse erben. Neben einfacher Vererbung ist
auch Mehrfachvererbung mit interfaces und dem Schlüsselwort `implements` möglich.

### Go
In Go wird Vererbung durch das Einbetten von types erreicht. Eine Struktur kann eine
andere Struktur einbetten, wodurch die eingebettete Struktur Eigenschaften und Methoden
der äußeren Struktur "erbt". Mehrfachvererbung, aber in einer flexibleren Form, wird durch
Mehrfacheinbettung erreicht. Die Vererbung in Go betont die Idee der Komposition und
ermöglicht die Wiederverwendung von Code durch das Einbetten von Strukturen.
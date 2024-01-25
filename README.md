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
Subtyping ist eine spezielle Form von Polymorphismus und bedeutet, dass ein Datentyp als Subtyp eines anderen betrachtet wird, wodurch Typen in verschiedenen Kontexten und Formen vielseitig verwendet werden können. 
Betrachten wir folgendes Beispiel: Es gibt einen Typ `Tier`, einen Typ `Katze` und einen Typ `Tiger`. Seien nun `Katze` und `Tiger` Subtypen von `Tier`, so können in einem Kontext in welchem der Typ `Tier` erwartet wird sowohl ein Ausdruck vom Typ `Katze` als auch ein Ausdruck vom Typ `Tiger` verwendet werden. Dies ermöglicht mehr Flexibilität und generischen Gebrauch von Datentypen in Programmiersprachen.
Nominales Subtyping und Strukturelles Subtyping sind zwei verschiedene Ansätze zur Umsetzung von Subtyp-Polymorphismus in Programmiersprachen.

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
    Shape s = new Rectangle(5, 6);  // This will throw a compile-time error due to the constraints of nominal subtyping (Shape is not implemented explicitly by Rectangle!)
}
```
Im obigen Beispiel kommt es zu einem Kompilierfehler da an der kommentierten Stelle `s` vom Typ `Shape` deklariert ist, jedoch versucht wird `s` eine Instanz vom Typ `Rectangle` zuzuweisen. Da `Rectangle` kein expliziter Subtyp von `Shape` ist, geht das nicht. Lösen könnte man diesen Fehler durch das Abändern von `class Rectangle` zu `class Rectangle extends Shape`.

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

In diesem Beispiel kommt es zu keinem Fehler aufgrund von Gos structural subtyping. Zwar ist `Rectangle` kein explizit deklarierter Subtyp von `Shape`, es implementiert jedoch alle Methoden von `Shape` und ist somit ein Subtyp davon. Folglich kann an der kommentierten Stelle, in welcher ein Ausdruck vom Typ `Shape` erwartet wird auch ein Ausdruck vom Type `Rectangle` verwendet werden.

## Vererbung

### Java
In Java wird Vererbung durch das Schlüsselwort `extends` ausgedrückt und
Klassenhierarchien kommen zum Einsatz. Eine Subklasse kann Eigenschaften und
Methoden von einer Superklasse bzw. Basisklasse erben. Neben einfacher Vererbung ist
auch Mehrfachvererbung mit interfaces und dem Schlüsselwort `implements` möglich. Ferner können die Implementierung von virtuelle Methoden in erbenden Klassen durch `@Override` überschrieben werden um mehr Flexibilität zu ermöglichen. Weiterhin können Methoden in Superklassen mit dem Keyword `abstract` als abstrakt deklariert werden, wodurch festgelegt wird, dass deren Implementierung erst in einer Subklasse stattfinden kann.

### Go
In Go wird Vererbung durch das Einbetten von types erreicht. Eine Struktur kann eine
andere Struktur einbetten, wodurch die eingebettete Struktur Eigenschaften und Methoden
der äußeren Struktur "erbt". Mehrfachvererbung, aber in einer flexibleren Form, wird durch
Mehrfacheinbettung erreicht. Die Vererbung in Go betont die Idee der Komposition und
ermöglicht die Wiederverwendung von Code durch das Einbetten von Strukturen.

## Verkapselung

### Java
In Java können Klassen mit `public`, `private` oder `protected` deklariert werden, um die
Zugriffs- und Sichtbarkeitsdefinition zu deklarieren.  Der Zugriff erfolgt dann ggfs. über Getter- und Setter-Methoden

### Go
In Go werden großgeschriebene Bezeichner zur Kennzeichnung `public` Sichtbarkeit verwendet und Kleinbuchstaben für `package-private` Sichtbarkeit.
Es gibt keine konkreten Äquivalente für `protected` oder `private`.
Go empfiehlt Entwicklern, Getter- und Setter-Methoden für die Kapselung zu verwenden, falls erforderlich.

## Interfaces

### Java
Java-Interfaces definieren Vorschriften, welche eine Klasse erfüllen muss.
Interfaces können sowohl Standardmethodenimplementierungen als auch statische Methoden enthalten.

### Go
Go-Interfaces werden implizit erfüllt, was bedeutet, dass es nicht notwendig ist, explizit anzugeben, dass ein Typ eine Interface implementiert.
Größere Interfaces können aus kleineren zusammengesetzt werden.

## Exception Handling
### Java
In Java wird das Exception Handling verwendet, um unerwartete Fehler oder
Ausnahmesituationen während der Laufzeit eines Programms zu behandeln. Zur
Fehlerbehandlung zur Verfügung stehen `try`, `catch`, `throw` und `finally`.

### Go
In Go wird im Gegensatz zu Java auf konventionelle Rückgabewerte für Fehlerbehandlung
gesetzt. In Go gibt es keine Exceptions oder herkömmlichen Try/Catch-Blöcke. Funktionen geben normalerweise einen speziellen Fehlerwert zurück, der vom
Aufrufer geprüft wird, um auf Fehler zu reagieren.

## Fazit
Die Objektorientierung in Java und Go verfolgen unterschiedliche Ansätze.
Java, als eine etablierte objektorientierte Sprache, betont klassische Konzepte wie
Klassen, Vererbung und Interfaces und bietet eine umfassende Standardbibliothek.
Go setzt im Gegensatz dazu auf eine einfachere und effizientere Herangehensweise, die
sich auf structs, Komposition, Einbettung von types und Interfaces stützt. 

## Vor- und Nachteile

### Java

| Vorteile                     | Nachteile          |
|------------------------------|--------------------|
| Plattformunabhängigkeit durch JVM        | Hohe Verbosität          |
| Umfangreiche Standartbibliothek             | Geringere Leistung        |
| Starke Community | Steilere Lernkurve / Komplexerer Syntax als Go|
|  Speichermanagement           | Speicherkonsumierung |

### Go

| Vorteile                     | Nachteile          |
|--------------------------------|----------------------------|
| Einfache Syntax     | Weniger etabliere Frameworks        |
| Nebenläufigkeit durch Goroutinen        | geringere Anzahl an Bibliotheken im Vergleich zu Java    |
| Unterstützung für Containerisierung                   | Kein Overloading     |
| Schnelle Kompilierung        | Keine explizite Fehlerbehandlung|

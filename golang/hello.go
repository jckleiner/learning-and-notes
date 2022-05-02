package main

import "fmt" // from the standard library
import "rsc.io/quote"

func main() {
    fmt.Println("todays quote: " + quote.Go())
    fmt.Println("quote from my function " + giveString())
}

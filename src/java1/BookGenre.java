/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

/**
 *
 * @author tadas
 */
public enum BookGenre {
    Fantasy("Fantastine"),
    Fiction("Grozine"),
    Psichology("Prishologija"),
    Science("Moksline"),
    Educational("Edukacine"),
    ForKids("Vaikams");
    
    String name;
    
    BookGenre(String name) {
        this.name = name;
    }
}

package com.dstrube.integertostring;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class Integer2StringTest {

    @org.junit.jupiter.api.Test
    @DisplayName("Tests of int2str")
    void int2str() {
        assertEquals("one", Integer2String.int2str(1),
                "Converting 1 to string");
        assertEquals("ten", Integer2String.int2str(10),
                "Converting 10 to string");
        assertEquals("twenty one", Integer2String.int2str(21),
                "Converting 21 to string");
        assertEquals("negative thirty one thousand four hundred fifteen", Integer2String.int2str(-31415),
                "Converting -31415 to string");
        assertEquals("two billion one hundred forty seven million four hundred eighty three thousand six hundred forty seven",
                Integer2String.int2str(Integer.MAX_VALUE), "Converting Integer.MAX_VALUE to string");
        assertEquals(
                "negative two billion one hundred forty seven million four hundred eighty three thousand six hundred forty eight",
                Integer2String.int2str(Integer.MIN_VALUE), "Converting Integer.MIN_VALUE to string");
        assertEquals("zero", Integer2String.int2str(0),
                "Converting 0 to string");
    }
}
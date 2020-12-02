package recommendation_library.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecommendationTest {
    @Test
    public void recommendationsWithSameTitleAndTypeAreEqual() {
        Recommendation eka = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        Recommendation toka = new Recommendation(12312312, "moi", Type.VIDEO, "ei", "joskus");
        assertTrue(eka.equals(toka));

        eka = new Recommendation(1, "moi", Type.BOOK, "joo", "nyt");
        toka = new Recommendation(12312312, "moi", Type.BOOK, "ei", "joskus");
        assertTrue(eka.equals(toka));
    }

    @Test
    public void recommendationsWithSameTitleButDifferentTypesAreNotEqual() {
        Recommendation eka = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        Recommendation toka = new Recommendation(12312312, "moi", Type.BOOK, "ei", "joskus");
        assertFalse(eka.equals(toka));
    }

    @Test
    public void recommendationsWithDifferentTitleAndTypesAreNotEqual() {
        Recommendation eka = new Recommendation(1, "pöö", Type.VIDEO, "joo", "nyt");
        Recommendation toka = new Recommendation(12312312, "moi", Type.BOOK, "ei", "joskus");
        assertFalse(eka.equals(toka));
    }

    @Test
    public void recommendationsWithDifferentTitlesButSameTypesAreNotEqual() {
        Recommendation eka = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        Recommendation toka = new Recommendation(12312312, "pöö", Type.VIDEO, "ei", "joskus");
        assertFalse(eka.equals(toka));

        eka = new Recommendation(1, "pöö", Type.BOOK, "joo", "nyt");
        toka = new Recommendation(12312312, "moi", Type.BOOK, "ei", "joskus");
        assertFalse(eka.equals(toka));
    }

    @Test
    public void getTypeReturnsCorrect() {
        Recommendation r = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        assertEquals(Type.VIDEO, r.getType());

        r = new Recommendation(1, "moi", Type.BOOK, "joo", "nyt");
        assertEquals(Type.BOOK, r.getType());
    }
    
    @Test
    public void setTypeChangesValue() {
        Recommendation r = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        r.setType(Type.BOOK);
        assertEquals(Type.BOOK, r.getType());

        r = new Recommendation(1, "moi", Type.BOOK, "joo", "nyt");
        r.setType(Type.VIDEO);
        assertEquals(Type.VIDEO, r.getType());
    }
    
    @Test
    public void getIdReturnsCorrect() {
        Recommendation r = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        assertEquals(1, r.getId());
    }

    @Test
    public void setIdChangesValue() {
        Recommendation r = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        r.setId(1023);
        assertEquals(1023, r.getId());
    }
    
    @Test
    public void setAddDateChangesValue() {
        Recommendation r = new Recommendation(1, "moi", Type.VIDEO, "joo", "nyt");
        r.setAddDate("eilen");
        assertEquals("eilen", r.getAddDate());
    }
}

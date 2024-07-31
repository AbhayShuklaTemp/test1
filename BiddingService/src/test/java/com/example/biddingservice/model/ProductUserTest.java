package com.example.biddingservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductUserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductUser#ProductUser()}
     *   <li>{@link ProductUser#setEmail(String)}
     *   <li>{@link ProductUser#setPassword(String)}
     *   <li>{@link ProductUser#setUserCategory(UserCategory)}
     *   <li>{@link ProductUser#setUserId(String)}
     *   <li>{@link ProductUser#setUserName(String)}
     *   <li>{@link ProductUser#getEmail()}
     *   <li>{@link ProductUser#getPassword()}
     *   <li>{@link ProductUser#getUserCategory()}
     *   <li>{@link ProductUser#getUserId()}
     *   <li>{@link ProductUser#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ProductUser actualProductUser = new ProductUser();
        actualProductUser.setEmail("jane.doe@example.org");
        actualProductUser.setPassword("iloveyou");
        actualProductUser.setUserCategory(UserCategory.Vendor);
        actualProductUser.setUserId("42");
        actualProductUser.setUserName("janedoe");
        String actualEmail = actualProductUser.getEmail();
        String actualPassword = actualProductUser.getPassword();
        UserCategory actualUserCategory = actualProductUser.getUserCategory();
        String actualUserId = actualProductUser.getUserId();

         
        assertEquals("42", actualUserId);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualProductUser.getUserName());
        assertEquals(UserCategory.Vendor, actualUserCategory);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductUser#ProductUser(String, String, String, UserCategory)}
     *   <li>{@link ProductUser#setEmail(String)}
     *   <li>{@link ProductUser#setPassword(String)}
     *   <li>{@link ProductUser#setUserCategory(UserCategory)}
     *   <li>{@link ProductUser#setUserId(String)}
     *   <li>{@link ProductUser#setUserName(String)}
     *   <li>{@link ProductUser#getEmail()}
     *   <li>{@link ProductUser#getPassword()}
     *   <li>{@link ProductUser#getUserCategory()}
     *   <li>{@link ProductUser#getUserId()}
     *   <li>{@link ProductUser#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ProductUser actualProductUser = new ProductUser("42", "janedoe", "jane.doe@example.org", UserCategory.Vendor);
        actualProductUser.setEmail("jane.doe@example.org");
        actualProductUser.setPassword("iloveyou");
        actualProductUser.setUserCategory(UserCategory.Vendor);
        actualProductUser.setUserId("42");
        actualProductUser.setUserName("janedoe");
        String actualEmail = actualProductUser.getEmail();
        String actualPassword = actualProductUser.getPassword();
        UserCategory actualUserCategory = actualProductUser.getUserCategory();
        String actualUserId = actualProductUser.getUserId();

         
        assertEquals("42", actualUserId);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualProductUser.getUserName());
        assertEquals(UserCategory.Vendor, actualUserCategory);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ProductUser#ProductUser(String, String, String, String, UserCategory)}
     *   <li>{@link ProductUser#setEmail(String)}
     *   <li>{@link ProductUser#setPassword(String)}
     *   <li>{@link ProductUser#setUserCategory(UserCategory)}
     *   <li>{@link ProductUser#setUserId(String)}
     *   <li>{@link ProductUser#setUserName(String)}
     *   <li>{@link ProductUser#getEmail()}
     *   <li>{@link ProductUser#getPassword()}
     *   <li>{@link ProductUser#getUserCategory()}
     *   <li>{@link ProductUser#getUserId()}
     *   <li>{@link ProductUser#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters3() {
        // Arrange and Act
        ProductUser actualProductUser = new ProductUser("42", "janedoe", "iloveyou", "jane.doe@example.org",
                UserCategory.Vendor);
        actualProductUser.setEmail("jane.doe@example.org");
        actualProductUser.setPassword("iloveyou");
        actualProductUser.setUserCategory(UserCategory.Vendor);
        actualProductUser.setUserId("42");
        actualProductUser.setUserName("janedoe");
        String actualEmail = actualProductUser.getEmail();
        String actualPassword = actualProductUser.getPassword();
        UserCategory actualUserCategory = actualProductUser.getUserCategory();
        String actualUserId = actualProductUser.getUserId();

         
        assertEquals("42", actualUserId);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualProductUser.getUserName());
        assertEquals(UserCategory.Vendor, actualUserCategory);
    }
}

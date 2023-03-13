Feature: product selection

  Scenario: choosing a product from products list and adding it to the cart

    Given that we navigate to home page
    * we accept cookies
    * we press Mein konto icon
    * we're logged in
    * we press  category Damen-Mode on a header menu
    * we press  bekleidung on a side menu
    * we press  blusen on a side menu
    * we press image from selected product on a gallery
    Then we see selected product in Product page
    * we press addToBasket button
    Then we see dialog window,that article moved to the basket
    * we close dialog window
    Then we see that article  is in basket
    * we press Warenkorb icon
    Then we see selected article in a Basket-form
    * we press delete Button in the basketItem
    Then selected product is deleted
    And we close the driver
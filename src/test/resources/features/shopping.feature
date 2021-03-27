#author: KatyGarcia kgb369y@gmail.com
@Shopping
Feature: Purchase flow 

Background: 
	Given the user is logged into the Oliver account 
	
@AddToCart
Scenario Outline: Add one or more productos to the shopping cart 
	Given the user go to shopping products
	When add the "<products>" to the shopping cart 
	Then the shopping cart will be appears with the "<products>" desired 
	
	Examples: 
		| products                           |
		| Verlaine Armchair, Rina Side Table |
		
@EditCart
Scenario Outline: Editing the shopping cart 
	Given the user has a shopping cart with the "<products already>" added 
	When add the "<products to add>" to the shopping cart 
	And remove the "<products to delete>" to the shopping cart
	Then the shopping cart will be appears with the "<finally products>" desired 
	
	Examples: 
		| products already              | products to add | products to delete | finally products                            |
		| Breuer Sectional, Breuer Sofa |                 |        Breuer Sofa | Breuer Sectional                            |
		| Breuer Sectional, Breuer Sofa | Coola Pillow    |                    | Breuer Sectional, Breuer Sofa, Coola Pillow |
		| Breuer Sectional, Breuer Sofa | Coola Pillow    |   Breuer Sectional | Breuer Sectional, Coola Pillow              |
		
		
@BuyProducts
Scenario Outline: Buy products to checking out in the Oliver web site 
	Given the user go to shopping products
	When add the "<products>" to the shopping cart 
	And give the assambly with checkout information 
	Then the user will have bought the desired product 
	
	Examples: 
		| products    |
		| Breuer Sofa |

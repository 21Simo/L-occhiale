/* Set rates + misc */
var taxRate = 0.05;
var shippingRate = 15.00; 
var fadeTime = 300;



$('.product-removal button').click( function() 
{
  removeItem(this);
});



/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
  
  /* Sum up row totals */
  $('.product').each(function () 
  {
    subtotal += parseFloat($(this).children('.product-line-price').text());
  });
  
  /* Calculate totals */
  var tax = subtotal * taxRate;
  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + tax + shipping;
  
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() 
  {
    $('#cart-subtotal').html(subtotal);
    $('#cart-tax').html(tax.toFixed(2));
    $('#cart-shipping').html(shipping.toFixed(2));
    $('#cart-total').html(total);
    if(total == 0)
    {
      $('.checkout').fadeOut(fadeTime);
    }
    else
    {
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}


/* Update quantity */
function updateQuantity(quantityInput)
{ 
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseFloat(productRow.children('.product-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  
  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function() 
    {
      $(this).text(linePrice);
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
}


/* Remove item from cart */
function removeItem(removeButton)
{
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
  productRow.slideUp(fadeTime, function() {
    productRow.remove();
    recalculateCart();
  });
}

//Quantità nuovo
function increaseCount(a, b) 
{
	var input = b.previousElementSibling;
  	var value = parseInt(input.value, 10);
  	if (value > 1)
  	{
		  value = isNaN(value) ? 0 : value;
		  value++;
		  input.value = value;
		  updateQuantity(input);
	}
}

function decreaseCount(a, b)
{
  	var input=$('#numeroQuantità')
  	var value = parseInt(input.value, 10);
  	if (value > 1) 
  	{
    	value = isNaN(value) ? 0 : value;
    	value--;
    	input.value = value;
    	updateQuantity(input);
  	} 	 
}
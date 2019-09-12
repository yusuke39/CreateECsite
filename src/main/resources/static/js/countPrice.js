
$(function(){
	
	$('.radio-inline').on('click', function(){
		var size = $('input[name=size]:checked').val();
		var topping_count = $('.toppings input:checkbox:checked').length;
		var piza_count = $("#pizaCount").val();
		console.log(size);
		
		if(size == 'm'){
			var sizePrice = $(".priceM").text();
			console.log(sizePrice);
			var toppingPrice = topping_count * 200;
		} else {
			sizePrice = $(".priceL").text();
			var toppingPrice = topping_count * 200;
		}
		
		var allPrice = (sizePrice + toppingPrice) * piza_count;
		$("#total-price").html('<input type="hidden" name="totalPrice">' + allPrice.toLocaleString());
		
	});
});
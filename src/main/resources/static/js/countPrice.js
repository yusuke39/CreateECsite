
$(function(){
	calc_price();
	
	$('.size').on('change', function(){
		calc_price();
	});
	
	$('.toppings').on('click', function(){
		calc_price();
	});
	
	$('#pizaCount').on('change',function(){
		calc_price();
	});
	
	
});
	
	
	function calc_price(){
		var size = $('input[name=size]:checked').val();
		var topping_count = $('.toppings input:checkbox:checked').length;
		var piza_count = $("#pizaCount").val();
		console.log(topping_count);
		
		if(size == 'm'){
			var sizePrice = parseInt($(".priceM").text().split(',').join('').trim());
			var toppingPrice = topping_count * 200;
		} else {
			sizePrice = parseInt($(".priceL").text().split(',').join('').trim());
			var toppingPrice = topping_count * 300;
		}
		
		
		var allPrice = (sizePrice + toppingPrice) * piza_count;
		$("#total-price").html('<input type="hidden" name="totalPrice">' + allPrice.toLocaleString());
		
	};
	
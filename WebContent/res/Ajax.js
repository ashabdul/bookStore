/**
 * 
 */
function doSimpleAjax(address, isbn){
	var request = new XMLHttpRequest();
	var data = "";

	/* add your code here to grab all parameters from form*/
	data = isbn;
	var url = address + "?addToCart=" + data;

	request.open("GET", url, true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
}

function handler(request)
{
	if ((request.readyState == 4) && (request.status == 200))
	{
		var bool = request.responseText;
		//var target = document.getElementById("ajaxTarget");
		if(bool == "true")
		{
			alert("Added to cart");
		}
		//target.innerHTML = request.responseText;
	}
} 
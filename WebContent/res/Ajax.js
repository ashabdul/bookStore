/**
 * 
 */
function doSimpleAjax(address){
	var request = new XMLHttpRequest();
	var data = "";

	/* add your code here to grab all parameters from form*/
	var data = document.getElementById("addtocart");

	request.open("POST", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

function handler(request)
{
	if ((request.readyState == 4) && (request.status == 200))
	{
		var boolean = request.responseText;
		//var target = document.getElementById("ajaxTarget");
		if(boolean.equals("true"))
		{
			alert("Added to cart");
		}
		//target.innerHTML = request.responseText;
	}
} 
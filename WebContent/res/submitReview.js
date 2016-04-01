/**
 * 
 */

function doAjax(address, id){
	var request = new XMLHttpRequest();
	var data = "";

	/* add your code here to grab all parameters from form*/
	data = document.getElementById("submitReview").value;
	var url = address + "?submitReview=" + data + "?bid=" + id;

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
		alert("Submitted review, thank you!");
	}
} 
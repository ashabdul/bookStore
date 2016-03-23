/**
 * 
 */
function validate() {
	//Clear any existing messages
	document.getElementById("error").innerHTML = "";
	
	var p = document.getElementById("username").value;
	if (p.length > 255) {
		document.getElementById("error").innerHTML = "Username must not exceed 255 characters";
		return false;
	}
	p = document.getElementById("password").value;
	if (p.length > 20){
		document.getElementById("error").innerHTML = "Password must not exceed 20 characters";
		return false;
	}
	return true;
}
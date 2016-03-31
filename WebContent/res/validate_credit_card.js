// takes the form field value and returns true on valid number
function isNumberKey(evt)
      {
		 var credit = document.getElementById("card").value;
         if (isNaN(credit) || credit.length < 16 || credit.length > 16){
        	 
        	 alert("Please Enter Valid Credit Card Number");
         }
         return true;
      }
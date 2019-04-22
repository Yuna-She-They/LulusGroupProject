var $ = function(id) {
    return document.getElementById(id);
};

window.onload = function() {
    $("testCC").onclick = process;
};

function process() {
	var strToTest = $("ccnumber").value;
	var isValid = Luhn(strToTest);
	if (isValid) {
            $("ccRes").innerHTML = "credit card number is valid";
	} else {
            $("ccRes").innerHTML = "credit card number is not valid";
        }
}


function Luhn(value) {
    // accept only digits, dashes or spaces
    if (/[^0-9-\s]+/.test(value)) return false;
    
    if (value === "") {
        return false;
    }
    
    //used to check that not all digits are zero, which would otherwise return true;
    var notZero = false;

    // Luhn
    var nCheck = 0, nDigit = 0, bEven = false;
    value = value.replace(/\D/g, "");

    for (var n = value.length - 1; n >= 0; n--) {
        nDigit = parseInt(value.charAt(n), 10);
        
        //zero check
        if (value.charAt(n) !== "0") {
            notZero=true;
        }
        
        if (bEven) {
                if ((nDigit *= 2) > 9) nDigit -= 9;
        }

        nCheck += nDigit;
        bEven = !bEven;
    }
    
    if (notZero===false) {
        return false;
    }
    
    return (nCheck % 10) === 0;
}

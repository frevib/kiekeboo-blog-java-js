var validateTitle = function() {
    //validate user input in the addpost form
    var title = document.forms["addpostangular"]["title"].value;
    document.forms["addpostangular"]["submit"].removeAttribute("disabled");
    document.getElementById("validationMessage").innerHTML = "";
    document.getElementById("validationLength").innerHTML = "";
    if(!(/^([\w, \.\?!])*$/.test(title))) {
        document.getElementById("validationMessage").innerHTML = "special characters are not allowed in title";
        document.forms["addpostangular"]["submit"].setAttribute("disabled", "disabled");
    }
    if(title.length > 50) {
        document.getElementById("validationLength").innerHTML = "max characters in title is 30";
        document.forms["addpostangular"]["submit"].setAttribute("disabled", "disabled");
    }
    console.log(title);
};

var validateBlogpostcontents = function() {
    var blogpostcontents = document.forms["addpostangular"]["contents"].value;
    document.forms["addpostangular"]["submit"].removeAttribute("disabled");
    var element = document.getElementById("validationLengthContents");
    element.innerHTML = "";
    if(blogpostcontents.length > 2500) {
        element.innerHTML = "max amount of characters is 2500";
        document.forms["addpostangular"]["submit"].setAttribute("disabled", "disabled");
    }
};


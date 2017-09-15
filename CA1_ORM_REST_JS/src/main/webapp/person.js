var getAllPersons = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/complete");
    promise.then(function (response) {
         if (response.status === 204) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There are currently no persons.";
        }
        return response.json();
    }).then(function (persons) {
        var mappedPersons = persons.map(function (person) {
            return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        console.log(mappedPersons);
        document.getElementById("tbody").innerHTML = mappedPersons;
    });
};

var getPersonByID = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/complete/" + document.getElementById("idNumber").value);
    promise.then(function (response) {
          if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no person with the following id " + document.getElementById("idNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid id";
        }
        return response.json();
    }).then(function (person) {
        var personByID = "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        var tableHeader = "<th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>";
        document.getElementById("idHeader").innerHTML = tableHeader;
        document.getElementById("idBody").innerHTML = personByID;
    });
};

var getPersonsByPhone = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/phone/" + document.getElementById("phoneNumber").value);
    promise.then(function (response) {
         if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no person with the following phone number " + document.getElementById("phoneNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid phone number";
        }
        return response.json();
  }).then(function (person) {
        var personByPhone = "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        var tableHeader = "<th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>";
        document.getElementById("phoneHeader").innerHTML = tableHeader;
        document.getElementById("phoneBody").innerHTML = personByPhone;
    });
};
var getPersonsByCity = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/city/" + document.getElementById("cityName").value);
    promise.then(function (response) {
       if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There are no persons in the following city " + document.getElementById("cityName").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid city name";
        }
        return response.json();
    }).then(function (persons) {
      var mappedCityPersons = persons.map(function (person) {
            return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        var cityHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("cityHeader").innerHTML = cityHeader;
        document.getElementById("cityBody").innerHTML = mappedCityPersons;
    });
};

var getPersonsByZipCode = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/zip/" + document.getElementById("zipCodeNumber"));
    promise.then(function (response) {
         if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no person with the following zip code " + document.getElementById("zipCodeNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid zip code";
        }
        return response.json();
    }).then(function (persons) {
          var mappedZipPersons = persons.map(function (person) {
         return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        var zipHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("zipHeader").innerHTML = zipHeader;
        document.getElementById("zipBody").innerHTML = mappedZipPersons;
    });
};

var postData = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            phones:
                    [
                        {
                            number: document.getElementById('number').value,
                            description: document.getElementById('descriptionPhone').value
                        }
                    ],
            address:
                    {
                        street: document.getElementById('street').value,
                        additionalinfo: document.getElementById('additionalinfo').value,
                        cityInfo: {
                            zipCode: document.getElementById('zipCodeModal').value,
                            city: document.getElementById('city').value
                        }
                        
                    }
         
        })
    }).then(function (response) {
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please fill up the required fields";
        }
        return response.json();
    });
};

var editData = function () {
     var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
            firstName: document.getElementById('editFirstName').value,
            lastName: document.getElementById('editLastName').value,
            email: document.getElementById('editEmail').value,
            phones:
                    [
                        {
                            number: document.getElementById('editNumber').value,
                            description: document.getElementById('editDescriptionPhone').value
                        }
                    ],
            address:
                    {
                        street: document.getElementById('editStreet').value,
                        additionalinfo: document.getElementById('editAdditionalinfo').value,
                        cityInfo: {
                            zipCode: document.getElementById('editZipCode').value,
                            city: document.getElementById('editCity').value
                        }
                        
                    }
         
        })
    }).then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Person not found";
        }
        return response.json();
    });
};

document.getElementById("id").addEventListener("click", getPersonByID);
document.getElementById("phone").addEventListener("click", getPersonsByPhone);
document.getElementById("city").addEventListener("click", getPersonsByCity);
document.getElementById("zipCode").addEventListener("click", getPersonsByZipCode);
document.getElementById("postData").addEventListener("click", postData);
document.getElementById("editData").addEventListener("click", editData);


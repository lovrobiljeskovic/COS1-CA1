var getAllPersons = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/complete");
    promise.then(function (response) {
        return response.json();
    }).then(function (persons) {
        var mappedPersons = persons.map(function (person) {
            return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        console.log(mappedPersons);
        document.getElementById("tbody").innerHTML = mappedPersons;
    });
};
getAllPersons();

var getPersonByID = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/contactinfo/" + document.getElementById("idNumber").value);
    promise.then(function (response) {
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
        return response.json();
   }).then(function (persons) {
        var mappedPhonePersons = persons.map(function (person) {
            return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        var phoneHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("phoneHeader").innerHTML = phoneHeader;
        document.getElementById("phoneBody").innerHTML = mappedPhonePersons;
    });
};
var getPersonsByCity = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/city/" + document.getElementById("cityName").value);
    promise.then(function (response) {
        return response.json();
    }).then(function (persons) {
      var mappedCityPersons = persons.map(function (person) {
            return "<tr><td>" + person.firstName + "</td>" + "<td>" + person.lastName + "</td>" + "<td>" + person.email + "</td>" + "<td>" + person.address+ "</td></tr>";
        }).join("");
        var cityHeaer = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("cityHeader").innerHTML = personByCityHeader;
        document.getElementById("cityBody").innerHTML = mappedCityPersons;
    });
};

var getPersonsByZipCode = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/person/zip/" + document.getElementById("zipCodeNumber"));
    promise.then(function (response) {
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
                            zipCode: document.getElementById('zipCode').value,
                            city: document.getElementById('city').value
                        }
                        
                    }
         
        })
    }).then(function (response) {
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
                            zipCode: document.getElementById('zipCode').value,
                            city: document.getElementById('city').value
                        }
                        
                    }
         
        })
    }).then(function (response) {
        return response.json();
    });
};
var deleteData = function () {
      var headers = {
        'Content-Type':'application/json'
    };
    var promise = fetch("http://localhost:8805/RESTexercise2/api/person/" + document.getElementById("deleteData").value, {
        method: "DELETE",
        headers: headers
    }).then(function (response) {
        return response.json();
    });
    getAllPersons();
    };

document.getElementById("id").addEventListener("click", getPersonByID);
document.getElementById("phone").addEventListener("click", getPersonsByPhone);
document.getElementById("city").addEventListener("click", getPersonsByCity);
document.getElementById("zipCode").addEventListener("click", getPersonsByZipCode);
document.getElementById("postData").addEventListener("click", postData);
document.getElementById("editData").addEventListener("click", editData);
document.getElementById("deleteId").addEventListener("click", deleteData);


var queryResults = [];
var tblHeaders = "<th>ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th>";

document.getElementById("tblhead").innerHTML = tblHeaders;

var reloadData = function () {
    var mappedPersons = queryResults.map(function (person) {
        return "<tr><td>" + person.id + "</td><td>"
                + person.firstName + "</td><td>"
                + person.lastName + "</td><td>"
                + person.email + "</td><td>"
                + "<a href=\"#\" class=\"deleteBtn btn btn-danger\" id=\"" + person.id + "\">delete</a> / "
                + "<a data-toggle=\"modal\" class=\"editBtn btn btn-default\" id=\"" + person.id + "\">edit</a>" +
                "</td></tr>";
    }).join("");
    document.getElementById("tblbody").innerHTML = mappedPersons;
    document.getElementById("tblhead").innerHTML = tblHeaders;
};

var reloadDataContactDetails = function () {
    var mappedPersons = queryResults.map(function (person) {
        return "<tr><td>" + person.email + "</td><td>"
                + person.phone + "</td><td>"
                + person.address + "</td><td>"
                + "<a href=\"#\" class=\"deleteBtn btn btn-danger\" id=\"" + person.id + "\">delete</a> / "
                + "<a data-toggle=\"modal\" class=\"editBtn btn btn-default\" id=\"" + person.id + "\">edit</a>" 
                + "</td></tr>";
    }).join("");
    document.getElementById("tblbody").innerHTML = mappedPersons;
    document.getElementById("tblhead").innerHTML = tblHeaders;
};

var getPersonByID = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/complete/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no person with the following ID: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid id";
        }
        tblHeaders = "<th>ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th>";
        return response.json();
    }).then(function (person) {
        queryResults = [];
        if (!person.hasOwnProperty("code")) {
            queryResults = [person];
            reloadData();
        }
    });
};

var getPersonByPhone = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/phone/" + document.getElementById("inputField").value);
    promise.then(function (response) {
         if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no person with the following phone number: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid phone number";
        }
        tblHeaders = "<th>E-mail</th><th>Phone number</th><th>Address</th>";
        return response.json();
  }).then(function (person) {
        queryResults = [];
        if (!person.hasOwnProperty("code")) {
            queryResults = [person];
            reloadDataContactDetails();
        }
    });
};
var getPersonsByCity = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/city/" + document.getElementById("inputField").value);
    promise.then(function (response) {
       if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There are no persons in the following city: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid city name";
        }
        tblHeaders = "<th>ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th>";
        return response.json();
    }).then(function (persons) {
        queryResults = [];
        if (!persons.hasOwnProperty("code")) {
            persons.map(function (person) {
                return queryResults.unshift(person);
            });
            reloadData();
        }
    });
};

var getPersonsByZipCode = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/zip/" + document.getElementById("inputField").value);
    promise.then(function (response) {
         if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no person with the following zip code " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid zip code";
        }
        tblHeaders = "<th>ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th>";
        return response.json();
    }).then(function (persons) {
        queryResults = [];
        if (!persons.hasOwnProperty("code")) {
            persons.map(function (person) {
                return queryResults.unshift(person);
            });
            reloadData();
        }
    });
};

var getPersonsByHobby = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/hobby/" + document.getElementById("inputField").value);
    promise.then(function (response) {
         if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no person with the following hobby:  " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid hobby.";
        }
        tblHeaders = "<th>ID</th><th>First Name</th><th>Last Name</th><th>E-mail</th>";
        return response.json();
    }).then(function (persons) {
        queryResults = [];
        if (!persons.hasOwnProperty("code")) {
            persons.map(function (person) {
                return queryResults.unshift(person);
            });
            reloadData();
        }
    });
};

var showAllPersonsContactDetails = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/contactinfo");
    promise.then(function (response) {
         if (response.status === 204) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no company registered on our platform";
        }
        tblHeaders = "<th>E-mail</th><th>Phone number</th><th>Address</th>";
        return response.json();
  }).then(function (person) {
        queryResults = [];
        if (!person.hasOwnProperty("code")) {
            queryResults = [person];
            reloadDataContactDetails();
        }
    });
};

var addPerson = function () {
    var phoneNumber;
    if (document.getElementById('newNumber').value === "") {
        phoneNumber = 0;
    } else {
        phoneNumber = document.getElementById('newNumber').value;
    }
    
    var headers = {
        'Content-Type': 'application/json'
    };
    
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            firstName: document.getElementById('newFirstName').value,
            lastName: document.getElementById('newLastName').value,
            email: document.getElementById('newEmail').value,
            phones:
                    [
                        {
                            number: phoneNumber,
                            description: document.getElementById('newDescriptionPhone').value
                        }
                    ],
            address:
                    {
                        street: document.getElementById('newStreet').value,
                        additionalInfo: document.getElementById('newAdditionalInfo').value,
                        cityInfo: {
                            zipCode: document.getElementById('newZip').value,
                            city: document.getElementById('newCity').value
                        }
                    }
        })
    }).then(function (response) {
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please fill up the required fields";
        }
        return response.json();
    }).then(function(person) {
        if (!person.hasOwnProperty("code")) {
            queryResults.unshift(person);
            reloadData();
        }
    });
};

var editPerson = function (e) {
    e.stopPropagation();
    console.log("Went into edit");
     var headers = {
        'Content-Type': 'application/json'
    };
    
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
            id: idToEdit,
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
                        additionalInfo: document.getElementById('editAdditionalInfo').value,
                        cityInfo: {
                            zipCode: document.getElementById('editZip').value,
                            city: document.getElementById('editCity').value
                        }
                    }
        })
    }).then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Person not found";
        }
        return response.json();
    }).then(function(person) {
        $("#editPersonModal").modal('toggle');
        queryResults = queryResults.filter(function (c) {
            return person.id !== c.id;
        });
        queryResults.unshift(person);
        reloadData();
    });
};

var deletePerson = function (e) {
    e.stopPropagation();
    
    if (e.target.className === "deleteBtn btn btn-danger") {
        console.log("Went into delete");
        var headers = {
            'Content-Type': 'application/json'
        };
        fetch("http://localhost:8080/CA1_ORM_REST_JS/api/person/" + e.target.id, {
            method: "DELETE",
            headers: headers
        }).then(function (response) {
            if (response.status === 404) {
                document.getElementById("warning").className += "alert alert-warning alert-dismissable";
                document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Person with following ID: " + e.target.id + " not found";
            }
            if (response.status === 400) {
                document.getElementById("warning").className += "alert alert-warning alert-dismissable";
                document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid ID";
            }
            return response.json();
        }).then(function (person) {
            queryResults = queryResults.filter(function (c) {
                return person.id !== c.id;
            });
            reloadData();
        });
    }
};

var optionController = function () {
    document.getElementById("warning").className = "";
    document.getElementById("warning").innerHTML = "";

    var option = document.getElementById("queryOptions").value;
    if (option === "idOption") {
        getPersonByID();
    }
    if (option === "zipOption") {
        getPersonsByZipCode();
    }
    if (option === "cityOption") {
        getPersonsByCity();
    }
    if (option === "phoneOption") {
        getPersonByPhone();
    }
    if (option === "hobbyOption") {
        getPersonsByHobby();
    }
};

var idToEdit;
var showEditModal = function (e) {
    console.log("Went into showEditModal");
    if (e.target.className === "editBtn btn btn-default") {
        e.stopPropagation();
        $("#editPersonModal").modal();
        idToEdit = e.target.id;
    }
};

document.getElementById("submitQueryBtn").addEventListener("click", optionController);
document.getElementById("tblbody").addEventListener("click", deletePerson);
document.getElementById("tblbody").addEventListener("click", showEditModal);
document.getElementById("editSubmitBtn").addEventListener("click", editPerson);
document.getElementById("addSubmitBtn").addEventListener("click", addPerson);
document.getElementById("seeAllPersonsBtn").addEventListener("click", showAllPersonsContactDetails);





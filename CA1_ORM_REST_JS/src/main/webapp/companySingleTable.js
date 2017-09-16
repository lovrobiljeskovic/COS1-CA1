var queryResults = [];
var tblheaders = "<th>ID</th><th>Name</th><th>CVR</th><th>Description</th>";
document.getElementById("tblhead").innerHTML = tblheaders;

var reloadData = function () {
    var mappedCompanies = queryResults.map(function (company) {
        return "<tr><td>" + company.id + "</td><td>"
                + company.name + "</td><td>"
                + company.cvr + "</td><td>"
                + company.description + "</td><td>"
                + "<a href=\"#\" class=\"deleteBtn btn btn-danger\" id=\"" + company.id + "\">delete</a> / "
                + "<a data-toggle=\"modal\" class=\"editBtn btn btn-default\" id=\"" + company.id + "\">edit</a>" +
                "</td></tr>";
    }).join("");
    document.getElementById("tblbody").innerHTML = mappedCompanies;
    document.getElementById("tblhead").innerHTML = tblheaders;

};

var getCompanyByID = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/id/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no company with the following id " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid id";
        }
        return response.json();
    }).then(function (company) {
        queryResults = [];
        if (!company.hasOwnProperty("code")) {
            queryResults = [company];
            reloadData();
        }
    });
};

var getCompanyByPhone = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/complete/phone/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no company with the following phone number: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid phone number";
        }
        return response.json();
    }).then(function (company) {
        queryResults = [];
        if (!company.hasOwnProperty("code")) {
            queryResults = [company];
            reloadData();
        }
    });
};

var getCompanyByCVR = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/cvr/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no company with the following CVR: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid cvr";
        }
        return response.json();
    }).then(function (company) {
        queryResults = [];
        if (!company.hasOwnProperty("code")) {
            queryResults = [company];
            reloadData();
        }
    });
};

var getCompaniesByZipCode = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/city/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There is no company with the following zip code:  " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid zip code";
        }
        return response.json();
    }).then(function (companies) {
        queryResults = [];
        if (!companies.hasOwnProperty("code")) {
            companies.map(function (company) {
                return queryResults.unshift(company);
            });
            reloadData();
        }
    });
};

var getCompaniesByMinEmployees = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/employees/more/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There are no companies with " + document.getElementById("inputField").value + " or more employees";
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid number";
        }
        return response.json();
    }).then(function (companies) {
        queryResults = [];
        if (!companies.hasOwnProperty("code")) {
            companies.map(function (company) {
                return queryResults.unshift(company);
            });
            reloadData();
        }
    });
};

var getCompaniesByMaxEmployees = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/employees/less/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> There are no companies with " + document.getElementById("inputField").value + " or less employees";
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid number";
        }
        return response.json();
    }).then(function (companies) {
        queryResults = [];
        if (!companies.hasOwnProperty("code")) {
            companies.map(function (company) {
                return queryResults.unshift(company);
            });
            reloadData();
        }
    });
};

var addCompany = function () {
    var phoneNumber;
    if (document.getElementById('newNumber').value === "") {
        phoneNumber = 0;
    } else {
        phoneNumber = document.getElementById('newNumber').value;
    }
    
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            email: document.getElementById('newEmail').value,
            name: document.getElementById('newName').value,
            description: document.getElementById('newDescription').value,
            cvr: document.getElementById('newCvr').value,
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
    }).then(function(company) {
        if (!company.hasOwnProperty("code")) {
            queryResults.unshift(company);
            reloadData();
        }
    });
};

var editCompany = function (e) {
    e.stopPropagation();
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
            id: idToEdit,
            email: document.getElementById('editEmail').value,
            name: document.getElementById('editName').value,
            description: document.getElementById('editDescription').value,
            cvr: document.getElementById('editCvr').value,
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
            document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Company not found";
        }
        return response.json();
    }).then(function(company) {
        $("#editCompanyModal").modal('toggle');
        queryResults = queryResults.filter(function (c) {
            return company.id !== c.id;
        });
        queryResults.unshift(company);
        reloadData();
    });
};

var deleteCompany = function (e) {
    e.stopPropagation();

    if (e.target.className === "deleteBtn btn btn-danger") {
        var headers = {
            'Content-Type': 'application/json'
        };
        fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/" + e.target.id, {
            method: "DELETE",
            headers: headers
        }).then(function (response) {
            if (response.status === 404) {
                document.getElementById("warning").className += "alert alert-warning alert-dismissable";
                document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Company with following id: " + e.target.id + ", not found";
            }
            if (response.status === 400) {
                document.getElementById("warning").className += "alert alert-warning alert-dismissable";
                document.getElementById("warning").innerHTML = "<strong>WOOPS!</strong> Please enter a valid id";
            }
            return response.json();
        }).then(function (company) {
            queryResults = queryResults.filter(function (c) {
                return company.id !== c.id;
            });
            reloadData();
        });
    }
};

var optionController = function () {
    document.getElementById("warning").className = "";
    document.getElementById("warning").innerHTML = "";

    var option = document.getElementById("queryOptions").value;
    if (option === "cvrOption") {
        getCompanyByCVR();
    }
    if (option === "zipOption") {
        getCompaniesByZipCode();
    }
    if (option === "idOption") {
        getCompanyByID();
    }
    if (option === "phoneOption") {
        getCompanyByPhone();
    }
    if (option === "minOption") {
        getCompaniesByMinEmployees();
    }
    if (option === "maxOption") {
        getCompaniesByMaxEmployees();
    }
};

var idToEdit;
var showEditModal = function (e) {
    if (e.target.className === "editBtn btn btn-default") {
        e.stopPropagation();
        $("#editCompanyModal").modal();
        idToEdit = e.target.id;
    }
};

document.getElementById("submitQueryBtn").addEventListener("click", optionController);
document.getElementById("tblbody").addEventListener("click", deleteCompany);
document.getElementById("tblbody").addEventListener("click", showEditModal);
document.getElementById("editSubmitBtn").addEventListener("click", editCompany);
document.getElementById("addSubmitBtn").addEventListener("click", addCompany);


var getAllCompanies = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/complete");
    promise.then(function (response) {
        if (response.status === 204) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is currently no companies.";
        }
        return response.json();

        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td>" + "<td>" + company.numEmployees + "</td>" + "<td>" + company.marketValue + "</td></tr>";
        }).join("");
        document.getElementById("tbody").innerHTML = mappedCompanies;
    });
};
getAllCompanies();

var getCompanyByPhone = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/complete/phone/" + document.getElementById("phoneNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following phone number " + document.getElementById("phoneNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid phone number";
        }
        return response.json();
    }).then(function (company) {
        var companyByPhone = "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        var tableHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("phoneHeader").innerHTML = tableHeader;
        document.getElementById("phoneBody").innerHTML = companyByPhone;
    });
};

var getCompanyByCVR = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/cvr/" + document.getElementById("cvrNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following cvr" + document.getElementById("cvrNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid cvr";
        }
        return response.json();
    }).then(function (company) {
        var companyByCVR = "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        var cvrHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("cvrHeader").innerHTML = cvrHeader;
        document.getElementById("cvrBody").innerHTML = companyByCVR;
    });
};
var getCompanyByID = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/id/" + document.getElementById("idNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following id " + document.getElementById("idNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid id";
        }
        return response.json();
    }).then(function (company) {
        var CompanyByID = "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        var idHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("idHeader").innerHTML = idHeader;
        document.getElementById("idBody").innerHTML = CompanyByID;
    });
};

var getCompanyByZipCode = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/city/" + document.getElementById("zipCodeNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following zip code " + document.getElementById("zipCodeNumber").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid zip code";
        }
        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var zipCodeHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("zipCodeHeader").innerHTML = zipCodeHeader;
        document.getElementById("zipCodeBody").innerHTML = mappedCompanies;
    });
};

var getCompaniesByMinEmployees = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/employees/less/" + document.getElementById("employeeMinNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with less than " + document.getElementById("employeeMinNumber").value + " employees";
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid number";
        }
        return response.json();
    }).then(function (companies) {
        var mappedMinEmployeeCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var employeeMinHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("employeeMinHeader").innerHTML = employeeMinHeader;
        document.getElementById("employeeMinBody").innerHTML = mappedMinEmployeeCompanies;
    });
};

var getCompaniesByMaxEmployees = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/employees/more/" + document.getElementById("employeeMaxNumber").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with more than " + document.getElementById("employeeMaxNumber").value + " employees";
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid number";
        }
        return response.json();
    }).then(function (companies) {
        var mappedMaxEmployeeCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var employeeMaxHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("employeeMaxHeader").innerHTML = employeeMaxHeader;
        document.getElementById("employeeMaxBody").innerHTML = mappedMaxEmployeeCompanies;
    });
};
var postData = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            email: document.getElementById('email').value,
            name: document.getElementById('name').value,
            description: document.getElementById('description').value,
            cvr: document.getElementById('cvrNum').value,
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
        return response.json();
    });
};

var editData = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
            email: document.getElementById('editEmail').value,
            name: document.getElementById('editName').value,
            description: document.getElementById('editDescription').value,
            cvr: document.getElementById('editCvrNum').value,
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
        return response.json();
    });
};
var deleteData = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/" + document.getElementById("deleteData").value, {
        method: "DELETE",
        headers: headers
    }).then(function (response) {
        return response.json();
    });
    getAllCompanies();
};

document.getElementById("phone").addEventListener("click", getCompanyByPhone);
document.getElementById("cvr").addEventListener("click", getCompanyByCVR);
document.getElementById("id").addEventListener("click", getCompanyByID);
document.getElementById("zipCode").addEventListener("click", getCompanyByZipCode);
document.getElementById("minNumber").addEventListener("click", getCompaniesByMinEmployees);
document.getElementById("maxNumber").addEventListener("click", getCompaniesByMaxEmployees);
document.getElementById("postData").addEventListener("click", postData);
document.getElementById("editData").addEventListener("click", editData);
document.getElementById("deleteId").addEventListener("click", deleteData);


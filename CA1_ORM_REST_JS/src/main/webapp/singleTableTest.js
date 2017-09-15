var queryResults = [];

var reloadData = function () {
    var mappedCompanies = queryResults.map(function (company) {
        return "<tr><td>" + company.id + "</td><td>"
                + company.name + "</td><td>"
                + company.cvr + "</td><td>"
                + company.description + "</td><td>" 
                + "<a href=\"#\" class=\"btndelete\" id=\"" + company.id + "\">delete</a> / "
                + "<a data-toggle=\"modal\" class=\"btnedit\" id=\"" + company.id + "\">edit</a>" +
                "</td></tr>";
    }).join("");
    document.getElementById("tblbody").innerHTML = mappedCompanies;

};

var getCompanyByCVR = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/cvr/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following CVR: " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid cvr";
        }
        return response.json();
    }).then(function (company) {
        if (!company.hasOwnProperty("code")) {
            queryResults = [company];
            reloadData();
            queryResults = [];
        }
    });
};

var getCompaniesByZipCode = function () {
    var promise = fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/city/" + document.getElementById("inputField").value);
    promise.then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> There is no company with the following zip code:  " + document.getElementById("inputField").value;
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid zip code";
        }
        return response.json();
    }).then(function (companies) {
        companies.map(function (company) {
            return queryResults.unshift(company);
        });
        reloadData();
        queryResults = [];
    });
};

var deleteCompany = function (e) {
    var headers = {
        'Content-Type': 'application/json'
    };
    fetch("http://localhost:8080/CA1_ORM_REST_JS/api/company/" + e.target.id, {
        method: "DELETE",
        headers: headers
    }).then(function (response) {
        if (response.status === 404) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Company with following id: " + e.target + ", not found";
        }
        if (response.status === 400) {
            document.getElementById("warning").className += "alert alert-warning alert-dismissable";
            document.getElementById("warning").innerHTML = "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a><strong>WOOPS!</strong> Please enter a valid id";
        }
        return response.json();
    }).then(function(company) {
        queryResults.filter(function(c) {
            return company.id !== c.id;
        });
        reloadData();
    });
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
};

var idToEdit;
var showEditModal = function(e) {
    if (e.target.className === "btnedit") {
        e.stopPropagation();
        $("#editPersonModal").modal(); 
        idToEdit = e.target.id;
    }
};

document.getElementById("submitQueryBtn").addEventListener("click", optionController);
document.getElementById("tblbody").addEventListener("click", deleteCompany);
document.getElementById("tblbody").addEventListener("click", showEditModal);

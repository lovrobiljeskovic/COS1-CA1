var getAllCompanies = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company/complete");
    promise.then(function (response) {
        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td>" + "<td>" + company.numEmployees + "</td>" + "<td>" + company.marketValue + "</td></tr>";
        }).join("");
        console.log(mappedCompanies);
        document.getElementById("tbody").innerHTML = mappedCompanies;
    });
};
getAllCompanies();

var getCompanyByPhone = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company/complete/phone/" + document.getElementById("phoneNumber").value);
     promise.then(function (response) {
        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var tableHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("phoneHeader").innerHTML = tableHeader;
        document.getElementById("phoneBody").innerHTML = mappedCompanies;
    });
};

var getCompanyByCVR = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company/cvr/" + document.getElementById("cvrNumber"));
     promise.then(function (response) {
        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var tableHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("cvrHeader").innerHTML = cvrHeader;
        document.getElementById("cvrBody").innerHTML = mappedCompanies;
    });
};
var getCompanyByID = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company/id/" + document.getElementById("idNumber"));
     promise.then(function (response) {
        return response.json();
    }).then(function (companies) {
        var mappedCompanies = companies.map(function (company) {
            return "<tr><td>" + company.name + "</td>" + "<td>" + company.description + "</td>" + "<td>" + company.cvr + "</td></tr>";
        }).join("");
        var idHeader = "<th>Company Name</th><th>Description</th><th>CVR</th>";
        document.getElementById("idHeader").innerHTML = idHeader;
        document.getElementById("idBody").innerHTML = mappedCompanies;
    });
};

var getCompanyByZipCode = function () {
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company/city/" + document.getElementById("zipCodeNumber"));
     promise.then(function (response) {
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
var postData = function () {
      var headers = {
        'Content-Type':'application/json'
    };
    var promise = fetch("http://localhost:8805/CA1_ORM_REST_JS/api/company", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            name: document.getElementById('name').value,
              description: document.getElementById('description').value,
                cvr: document.getElementById('cvr').value,
                
        })
    }).then(function (response) {
        return response.json();
    });
    };
    
    document.getElementById("phone").addEventListener("click", getCompanyByPhone);
    document.getElementById("cvr").addEventListener("click", getCompanyByCVR);
      document.getElementById("id").addEventListener("click", getCompanyByID);
       document.getElementById("zipCode").addEventListener("click", getCompanyByZipCode);

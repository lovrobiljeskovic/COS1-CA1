var createTable = function () {
    var promise = fetch("http://localhost:8080/RestPerson/api/person");
    promise.then(function (response) {
        return response.json();
    }).then(function (persons) {
        var mappedPersons = persons.map(function (person) {
            return "<tr><td>" + person.id + "</td>" + "<td>" + person.fName + "</td>" + "<td>" + person.lName + "</td>" + "<td>" + person.phone + "</td>";
        }).join("");
        console.log(mappedPersons);
        document.getElementById("tbody").innerHTML = mappedPersons;
    });
};

var createPerson = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/RestPerson/api/person", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
            fName: document.getElementById('createfName').value,
            lName: document.getElementById('createlName').value,
            phone: document.getElementById('createphone').value
        })
    }).then(function (response) {
        return response.json();
    });
};

var deletePerson = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/RestPerson/api/person/" + document.getElementById("deleteId").value, {
        method: "DELETE",
        headers: headers
    }).then(function (response) {
        return response.json();
    });
};

var editPerson = function () {
    var headers = {
        'Content-Type': 'application/json'
    };
    var promise = fetch("http://localhost:8080/RestPerson/api/person", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
            id: document.getElementById('editId').value,
            fName: document.getElementById('editfName').value,
            lName: document.getElementById('editlName').value,
            phone: document.getElementById('editphone').value
        })
    }).then(function (response) {
        return response.json();
    });
};

function getByID(){
        var promise = fetch('http://localhost:8080/CA1_ORM_REST_JS/api/person/complete/'+ document.getElementById("IdgetById"));
    promise.then(function (response) {
        return response.json();
    }).then(function (Person) {
        document.getElementById("hours").innerText = CurrentSystemTime.hours;
        document.getElementById("minutes").innerText = CurrentSystemTime.minutes;
        document.getElementById("seconds").innerText = CurrentSystemTime.seconds;
    });
}


document.getElementById("getByID").addEventListener("click", getByID);
document.getElementById("createPerson").addEventListener("click", createPerson);
document.getElementById("deletePerson").addEventListener("click", deletePerson);
document.getElementById("editPerson").addEventListener("click", editPerson);
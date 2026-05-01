const BASE_URL = "http://localhost:8080";

let selectedClass = "";
let currentTrainNumber = "";
let passengers = [];


// ================= SHOW USER =================
window.onload = function () {

    const username = localStorage.getItem("username");

    if (!username) {
        alert("Please login first");
        window.location.href = "index.html";
        return;
    }

    document.getElementById("user").innerText = username;

};


// ================= LOAD TRAIN DETAILS =================
function loadTrain(trainNumber){

currentTrainNumber = trainNumber;

fetch(BASE_URL + "/reservation/search?trainNumber=" + trainNumber)
.then(res => {

if(!res.ok){
throw new Error("Train not found");
}

return res.json();

})
.then(data => {

selectedClass = "";

document.getElementById("classType").value = "";
document.getElementById("seatInfo").innerHTML = "";

document.getElementById("coachButtons").innerHTML = `

<button class="coach-btn" onclick="selectClass('1AC',${data.ac1Seats},this)">
1AC (${data.ac1Seats})
</button>

<button class="coach-btn" onclick="selectClass('2AC',${data.ac2Seats},this)">
2AC (${data.ac2Seats})
</button>

<button class="coach-btn" onclick="selectClass('3AC',${data.ac3Seats},this)">
3AC (${data.ac3Seats})
</button>

<button class="coach-btn" onclick="selectClass('SLEEPER',${data.sleeperSeats},this)">
Sleeper (${data.sleeperSeats})
</button>

<button class="coach-btn" onclick="selectClass('2S',${data.secondSeaterSeats},this)">
2S (${data.secondSeaterSeats})
</button>

`;

})
.catch(err=>{
alert(err.message);
});

}


// ================= SELECT COACH =================
function selectClass(type,seats,button){

if(seats <= 0){
alert("No seats available in " + type);
return;
}

selectedClass = type;

document.getElementById("classType").value = type;

document.querySelectorAll(".coach-btn")
.forEach(btn => btn.classList.remove("selected"));

button.classList.add("selected");

document.getElementById("seatInfo").innerHTML =
"Available Seats in " + type + ": " + seats;

}


// ================= ADD PASSENGER =================
function addPassenger(){

const container = document.getElementById("passengerContainer");

const index = passengers.length;

container.innerHTML += `

<div class="passenger-box">

Name: <input type="text" id="name${index}">

Age: <input type="number" id="age${index}">

Gender:

<select id="gender${index}">
<option value="Male">Male</option>
<option value="Female">Female</option>
</select>

Berth:

<select id="berth${index}">
<option value="LOWER">Lower</option>
<option value="MIDDLE">Middle</option>
<option value="UPPER">Upper</option>
<option value="SIDE_LOWER">Side Lower</option>
<option value="SIDE_UPPER">Side Upper</option>
</select>

</div>

`;

passengers.push(index);

}


// ================= CONFIRM BOOKING =================
function confirmBooking(){

const username = localStorage.getItem("username");

const journeyDate = document.getElementById("journeyDate").value;

if(!currentTrainNumber){
alert("Select a train first");
return;
}

if(!selectedClass){
alert("Please select a coach");
return;
}

if(!journeyDate){
alert("Select journey date");
return;
}

let passengerList = [];

passengers.forEach(i=>{

const name = document.getElementById("name"+i).value;
const age = document.getElementById("age"+i).value;
const gender = document.getElementById("gender"+i).value;
const berth = document.getElementById("berth"+i).value;

if(name && age){

passengerList.push({

name:name,
age:parseInt(age),
gender:gender,
berthPreference:berth

});

}

});

if(passengerList.length === 0){
alert("Add at least one passenger");
return;
}

fetch(BASE_URL + "/reservation/book",{

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify({

username:username,
trainNumber:currentTrainNumber,
classType:selectedClass,
journeyDate:journeyDate,
passengers:passengerList

})

})

.then(res=>{

if(!res.ok){
throw new Error("Booking failed");
}

return res.json();

})

.then(data=>{

alert(

"Booking Successful\n\n" +
"PNR: " + data.pnrNumber +
"\nStatus: " + data.status

);

passengers = [];

document.getElementById("passengerContainer").innerHTML = "";

loadTrain(currentTrainNumber);

})

.catch(err=>{
alert(err.message);
});

}


// ================= VIEW STATUS =================
function viewStatus(){

const username = localStorage.getItem("username");

fetch(BASE_URL + "/reservation/status?username=" + username)

.then(res=>res.json())

.then(data=>{

let output = "";

data.forEach(r=>{

output += `

<hr>

PNR: ${r.pnrNumber}

<br>Train: ${r.train.trainName}

<br>Status: ${r.status}

<br>Passengers:

`;

r.passengers.forEach(p=>{

output += `<br>- ${p.name} (${p.age}) Seat: ${p.seatNumber}`;

});

});

document.getElementById("reservationList").innerHTML = output;

});

}


// ================= LOGOUT =================
function logout(){

localStorage.removeItem("username");

window.location.href = "index.html";

}


// ================= TRAIN CARD CLICK =================
function selectTrain(trainNumber){

loadTrain(trainNumber);

window.scrollTo({

top:document.getElementById("coachButtons").offsetTop,
behavior:"smooth"

});

}


// ================= SHOW ALL TRAINS =================
function showAllTrains(){

fetch(BASE_URL + "/reservation/trains")

.then(res=>res.json())

.then(data=>{

let cards = `<div class="train-card-container">`;

data.forEach(train=>{

cards += `

<div class="train-card" onclick="selectTrain('${train.trainNumber}')">

<div class="train-title">${train.trainName}</div>

<br>

Train No: ${train.trainNumber}

<br>

From: ${train.fromStation}

<br>

To: ${train.toStation}

</div>

`;

});

cards += `</div>`;

document.getElementById("trainCards").innerHTML = cards;

});

}


// ================= SEARCH BY STATIONS =================
function searchByStations(){

const from = document.getElementById("fromStation").value;
const to = document.getElementById("toStation").value;

fetch(BASE_URL + "/reservation/searchStations?from="+from+"&to="+to)

.then(res=>res.json())

.then(data=>{

let cards = `<div class="train-card-container">`;

data.forEach(train=>{

cards += `

<div class="train-card" onclick="selectTrain('${train.trainNumber}')">

<div class="train-title">${train.trainName}</div>

<br>

Train No: ${train.trainNumber}

<br>

From: ${train.fromStation}

<br>

To: ${train.toStation}

</div>

`;

});

cards += `</div>`;

document.getElementById("trainCards").innerHTML = cards;

});

}
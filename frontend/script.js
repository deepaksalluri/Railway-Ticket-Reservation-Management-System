const BASE_URL = "http://localhost:8080";

/* =========================
   REGISTER FUNCTION
========================= */
function register() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!username || !password) {
        alert("Please enter username and password");
        return;
    }

    fetch(BASE_URL + "/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => response.text())
    .then(data => {
        console.log("Register response:", data);
        alert(data);
    })
    .catch(error => {
        console.error("Register error:", error);
        alert("Registration failed. Backend may not be running.");
    });
}


/* =========================
   LOGIN FUNCTION
========================= */
function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!username || !password) {
        alert("Please enter username and password");
        return;
    }

    fetch(BASE_URL + "/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => response.text())
    .then(data => {

        console.log("Login response:", data);

        // safer success check
        if (data.toLowerCase().includes("success")) {

            // store username
            localStorage.setItem("username", username);

            // redirect
            window.location.href = "dashboard.html";

        } else {
            alert(data);
        }

    })
    .catch(error => {
        console.error("Login error:", error);
        alert("Login failed. Backend may not be running.");
    });
}
// Attach button events after page loads
window.onload = function () {
    document.getElementById("loginBtn").addEventListener("click", login);
    document.getElementById("registerBtn").addEventListener("click", register);
};


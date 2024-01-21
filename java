// app.js
let sheetId = '1vAKDIOJPuryu-irPvjiKX9UEFa9JCq46bworOFwoKq4';
let range = 'Sheet1!A:B';
let apiKey = 'AIzaSyDnbVryiI3tFZZ3JDv4QdILfMbG1cxZ_RA';

function authenticate() {
  gapi.load('client', initClient);
}

function initClient() {
  gapi.client.init({
    apiKey: apiKey,
    discoveryDocs: ['https://sheets.googleapis.com/$discovery/rest?version=v4'],
  }).then(() => {
    checkCredentials();
  });
}

function checkCredentials() {
  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;

  gapi.client.sheets.spreadsheets.values.get({
    spreadsheetId: sheetId,
    range: range,
  }).then((response) => {
    let data = response.result.values;
    let validCredentials = false;

    if (data) {
      data.forEach((row) => {
        if (row[0] === username && row[1] === password) {
          validCredentials = true;
        }
      });
    }

    if (validCredentials) {
      alert('Login successful!');
      // Redirect or perform other actions upon successful login
    } else {
      alert('Invalid username or password. Please try again.');
    }
  });
}

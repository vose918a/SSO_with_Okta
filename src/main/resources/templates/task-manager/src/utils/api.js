const API_URL = 'http://localhost:8080/task'

async function fetchTask(){

    const token = 'eyJraWQiOiJKNThXZWpHa0lERHNaQ1I5amFGdThBdG1xNGVuRUVQbks0X0d2cmN2QjFRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkFIWDZXd1RyUjVwOG5SbENTTV9LYkNHWXJfUzR2bVlfTzRPWjE4QmpyQ0UiLCJpc3MiOiJodHRwczovL2Rldi02MDM2OTcwMC5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE3MTE1NjI2MDEsImV4cCI6MTcxMTU2NjIwMSwiY2lkIjoiMG9hZjdxbXhhOHlkaTBBblY1ZDciLCJ1aWQiOiIwMHVmNzRuNnJsV0JRZjJKbTVkNyIsInNjcCI6WyJjb2RlY29kZSJdLCJhdXRoX3RpbWUiOjE3MTE1NjI1OTYsInN1YiI6ImNtbWVkaW5hQGZjdHVuY2EuZWR1LnB5In0.oAE4H57RdeJjpNZiFRojNKbouBghZxqVIH96vlR5_H-j9MA3oPIOyHYjQUcNwLtZ-iIE62KFc5jFF7ub9YeR4dUKiNB8pEC0MxYTNHtecBJziAUuQMBd75TzF-sCpbasi6Icg1TRu4pU_k5wLKb6O803YAQMMX0ZKCe-Kd6MEk7Uzf2wQcTzJjklY5e18Z6T-j79K6jOaIRWH5bP4idwubb1tQADS3NtJWSXB8dy4PhcR43juIpttdPcYCNPoXU4O_Ns336kXgn2dvNGt3FWMB-CS-0Bf5XHnRxqJhUuFyT3QailMRJw0u00adT0hlqL4LwbITnwCWqLIeAwWeCXgg';

    try {
        const response = await fetch(API_URL.concat('/getTasks'), {
            headers: {
                Authorization: 'Bearer ' + token,
            },
        });
        if (!response.ok) {
            throw new Error('Failed to fetch tasks list')
        }
        return await response.json();
    } catch (error){
        console.error(error);
        throw error;
    }
}

export {fetchTask};
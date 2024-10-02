# ClickUp API Postman Collection

## Overview
This repository contains a Postman collection designed for interacting with the ClickUp API, specifically focusing on operations related to the "Get Goals" endpoints. It's equipped with pre-configured requests for fetching, creating, updating, and deleting goals.

## Prerequisites
Before you begin using this collection, ensure you have:
- **Postman** installed on your machine. Download it from [Postman's official website](https://www.postman.com/downloads/).
- A valid **ClickUp API token**. Obtain it by following the [Getting Started guide](https://docs.clickup.com/en/articles/1367130-getting-started-with-the-clickup-api) from ClickUp.

## Installation

### Cloning the Repository
To get started with this collection, clone this repository to your local machine:
```bash
git clone https://github.com/ypohranychnyy/r_d_apiTesting.git
```

## Importing into Postman
Open Postman.
Click on Import > Choose Files.
Select the 1_ClickUp.postman_collection.json file that you've cloned.
Setting Up the Environment
Navigate to the environment settings in Postman.
Ensure the following variables are configured:
api_token: Your ClickUp API token.
base_url: Set this to https://api.clickup.com/api/v2.

## Collection Details
The collection includes:

Get Goals: Retrieve all goals associated with a specified team.
Create Goal: Add a new goal to a team.
Update Goal: Modify details of an existing goal.
Delete Goal: Remove a goal permanently.
Each request includes pre-request scripts and test scripts to validate the API responses and handle dynamic data.

## Contributing
Contributions are welcome! If you have suggestions or improvements:

Fork the repository.
Create your feature branch (git checkout -b feature/AmazingFeature).
Commit your changes (git commit -am 'Add some AmazingFeature').
Push to the branch (git push origin feature/AmazingFeature).
Open a pull request.

const BASE_URL = "http://localhost:8080/api/users";

export async function getUsers() {
    const response = await fetch(BASE_URL);

    if (!response.ok) {
        throw new Error("Failed to fetch users");
    }

    return response.json();
}

export async function getUserById(userId) {
    const response = await fetch(`${BASE_URL}/${userId}`);

    if (!response.ok) {
        throw new Error("Failed to fetch a user");
    }

    return response.json();
}

export async function createUser(user) {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        throw new Error("Failed to create user");
    }

    return response.json();
}

export async function updateUser(userId, user) {
    const response = await fetch(`${BASE_URL}/${userId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        throw new Error("Failed to update user");
    }

    return response.json();
}

export async function deleteUser(userId) {
    const response = await fetch(`${BASE_URL}/${userId}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Failed to delete user");
    }
}
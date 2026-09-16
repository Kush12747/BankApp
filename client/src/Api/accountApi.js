const BASE_URL = "http://localhost:8080/api/accounts";

export async function getAccount(accountId) {
    const response = await fetch(`${BASE_URL}/${accountId}`);

    if (!response.ok) {
        throw new Error("Failed to fetch account");
    }

    return response.json();
}

export async function createAccount(account) {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(account)
    });

    if (!response.ok) {
        throw new Error("Failed to create account");
    }

    return response.json();
}

export async function deposit(accountId, amount) {
    const response = await fetch(
        `${BASE_URL}/${accountId}/deposit/${amount}`,
        {
            method: "POST"
        }
    );

    if (!response.ok) {
        throw new Error("Deposit failed");
    }

    return response.json();
}

export async function withdraw(accountId, amount) {
    const response = await fetch(
        `${BASE_URL}/${accountId}/withdraw/${amount}`,
        {
            method: "POST"
        }
    );

    if (!response.ok) {
        throw new Error("Withdrawal failed");
    }

    return response.json();
}

export async function getTransactions(accountId) {
    const response = await fetch(
        `${BASE_URL}/${accountId}/transactions`
    );

    if (!response.ok) {
        throw new Error("Failed to fetch transactions");
    }

    return response.json();
}
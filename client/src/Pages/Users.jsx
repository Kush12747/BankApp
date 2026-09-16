import { useEffect, useState } from "react";
import { 
    getUsers,
    getUserById,
    updateUser,
    deleteUser,
    createUser
 } from "../Api/userApi";

function Users() {
    
    // List of users from the backend
    const [users, setUsers] = useState([]);

    // Form Data
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    // If this has a value, we're editing a user
    const [editingUserId, setEditingUserId] = useState(null);

    // Used to display errors
    const [error, setErrors] = useState("");

    // used to show loading while getting users
    const [loading, setLoading] = useState(true);
    

    // Load users when the page first opens
    useEffect(() => {
        loadUsers();
    }, []);

    async function loadUsers() {
        try {
            setLoading(false);
            setErrors("");

            const data = await getUsers();

            setUsers(data);
        } catch(err) {
            setErrors(err);
        } finally {
            setLoading(false);
        }
    }

    // Create or update user
    async function handleSubmit(event) {

        // Prevent browser from refreshing the page
        event.preventDefault();

        try {
            setErrors("");

            const user = {
                name: name,
                email: email
            };

            if (editingUserId) {
                
                //Update
                const updatedUser = await updateUser(editingUserId, user);

                // Replace the old user with the updated user
                setUsers(previousUsers => 
                    previousUsers.map(existingUser =>
                        existingUser.userId === editingUserId
                            ? updatedUser
                            : existingUser
                    ));
            } else {

                // Create user
                const newUser = await createUser(user);

                // Add the new user to our existing list
                setUsers(previousUsers => [...previousUsers, newUser]);
            }

            clearForm();
        } catch(err) {
            setErrors(err);
        }
    }

    //put the selected user's information into the form for editing
    function handleEdit(user) {
        setEditingUserId(user.userId);
        setName(user.name);
        setEmail(user.email);
    }

    // Delete a user
    async function handleDelete(userId) {
        const confirmed = window.confirm("Are you sure you want to delete this user?");

        if (!confirmed) {
            return;
        }

        try {
            setErrors("");

            await deleteUser(userId);

            // Remove deleted user from the UI
            setUsers(previousUsers =>
                previousUsers.filter(
                    user => user.userId !== userId
                )
            );

        } catch (err) {
            setErrors(err.message);
        }
    }

    // Reset the form
    function clearForm() {

        setName("");
        setEmail("");
        setEditingUserId(null);
    }


    if (loading) {
        return <p>Loading users...</p>;
    }


    return (
        <div className="users-page">

            <h1>Users</h1>

            {error && (
                <div className="error">
                    {error}
                </div>
            )}


            {/* CREATE / UPDATE FORM */}
            <div className="user-form">

                <h2>
                    {editingUserId
                        ? "Update User"
                        : "Create User"}
                </h2>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">
                        <label>Name</label>

                        <input
                            type="text"
                            value={name}
                            onChange={event =>
                                setName(event.target.value)
                            }
                            placeholder="Enter name"
                            required
                        />
                    </div>


                    <div className="form-group">
                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            onChange={event =>
                                setEmail(event.target.value)
                            }
                            placeholder="Enter email"
                            required
                        />
                    </div>


                    <button type="submit">
                        {editingUserId
                            ? "Update User"
                            : "Create User"}
                    </button>


                    {editingUserId && (
                        <button
                            type="button"
                            onClick={clearForm}
                            className="cancel-button"
                        >
                            Cancel
                        </button>
                    )}

                </form>
            </div>


            {/* USER LIST */}
            <div className="users-list">

                <h2>All Users</h2>

                {users.length === 0 ? (
                    <p>No users found.</p>
                ) : (

                    users.map(user => (

                        <div
                            className="user-card"
                            key={user.userId}
                        >

                            <div>
                                <h3>{user.name}</h3>

                                <p>
                                    {user.email}
                                </p>

                                <small>
                                    ID: {user.userId}
                                </small>
                            </div>


                            <div className="user-actions">

                                <button
                                    onClick={() =>
                                        handleEdit(user)
                                    }
                                >
                                    Edit
                                </button>


                                <button
                                    onClick={() =>
                                        handleDelete(user.userId)
                                    }
                                    className="delete-button"
                                >
                                    Delete
                                </button>

                            </div>

                        </div>

                    ))
                )}

            </div>

        </div>
    );
}

export default Users;
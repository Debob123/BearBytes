import React, { useState, useEffect } from 'react';

function ProfilePage() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        // Fetch user info from API and setUser
    }, []);

    return (
        <div>
            <h1>Profile Page</h1>
            {user && (
                <div>
                    <h2>{user.name}</h2>
                    <p>{user.email}</p>
                </div>
            )}
        </div>
    );
}

export default ProfilePage;
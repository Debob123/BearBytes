import React from 'react';
import ManagerNavigation from "../managerPageComponents/ManagerNavigation";

function ManagerHome() {
    return (
        <div>
            <ManagerNavigation />
            <h1 className="content center"> Welcome to the Manager Home Page </h1>
        </div>
    );
}

export default ManagerHome;
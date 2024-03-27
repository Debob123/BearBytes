import ClerkHeader from '../clerkPageComponents/ClerkHeader';
import ClerkRoomDisplay from '../clerkPageComponents/ClerkRoomDisplay';
import './clerkRoomPage.css';


function ClerkRoomPage() {
    return (
        <div>
            <ClerkHeader/>
            <h1>Room Management</h1>
            <ClerkRoomDisplay/>
        </div>
    )
} 

export default ClerkRoomPage;
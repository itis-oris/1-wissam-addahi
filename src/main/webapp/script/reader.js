// This can be used to handle notification visibility or interactions if needed
document.addEventListener('DOMContentLoaded', function() {
    const notifications = document.querySelectorAll('.notification');
    notifications.forEach(notification => {
        setTimeout(() => {
            notification.style.display = 'none';
        }, 60000); // Hide notification after 5 seconds
    });
});

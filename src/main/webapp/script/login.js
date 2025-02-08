const roleSelect = document.getElementById("role");
const roleDiv = document.getElementById("roleDiv");

roleSelect.addEventListener("change", () => {
    const role = roleSelect.value;

    if (role === "worker") {
        roleDiv.innerHTML = `
           
                <label for="userW">Username:</label>
                <input id="userW" type="text" name="userName" placeholder="Enter your username">
           <br>
                <label for="passW">Password:</label>
                <input id="passW" type="password" name="password" placeholder="Enter your password">
        `;
    } else if (role === "admin") {
        roleDiv.innerHTML = `
        
           
                <label for="adminPas">Administrator Password:</label>
                <input id="adminPas" type="password" name="adminPassword" placeholder="Enter admin password">
            
        `;
    } else {
        roleDiv.innerHTML = `
        
            
                <label for="numberR">Phone Number:</label>
                <input id="numberR" type="tel" name="telNum" placeholder="Enter your phone number">
            <br>
                <label for="cardR">Library Card Number:</label>
                <input id="cardR" type="password" name="cardNum" placeholder="Enter your library card number">
            
        `;
    }
});


const starsContainer = document.querySelector('.stars');
const numStars = 200; // زيادة عدد النجوم للحصول على كثافة أكبر

// توليد النجوم بشكل عشوائي
for (let i = 0; i < numStars; i++) {
    const star = document.createElement('div');
    star.classList.add('star');

    // تحديد حجم النجوم بشكل عشوائي
    const size = Math.random() + 0.1; // النجوم بين 0.5px و 2.5px
    star.style.width = `${size}px`;
    star.style.height = `${size}px`;

    // تحديد مكان النجوم بشكل عشوائي (لتغطية الشاشة بالكامل)
    star.style.left = `${Math.random() * 100}%`;
    star.style.top = `${Math.random() * 100}%`;

    // إضافة تأخير ومدة عشوائية للحركة
    star.style.animationDuration = `${Math.random() * 5 + 5}s`; // بين 5 إلى 10 ثوانٍ
    star.style.animationDelay = `${Math.random() * 5}s`;

    starsContainer.appendChild(star);
}

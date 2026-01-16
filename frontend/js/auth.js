/**
 * Authentication utility functions for JWT token management
 */

const API_BASE_URL = 'http://localhost:8080/api';

/**
 * Save JWT token to localStorage
 */
function saveToken(token) {
    localStorage.setItem('token', token);
}

/**
 * Get JWT token from localStorage
 */
function getToken() {
    return localStorage.getItem('token');
}

/**
 * Remove JWT token from localStorage
 */
function removeToken() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
}

/**
 * Check if user is authenticated
 */
function isAuthenticated() {
    return getToken() !== null;
}

/**
 * Get authorization header for API requests
 */
function getAuthHeader() {
    const token = getToken();
    return token ? { 'Authorization': `Bearer ${token}` } : {};
}

/**
 * Save user data to localStorage
 */
function saveUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
}

/**
 * Get user data from localStorage
 */
function getUser() {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
}

/**
 * Redirect to login if not authenticated
 */
function requireAuth() {
    if (!isAuthenticated()) {
        window.location.href = 'login.html';
    }
}

/**
 * Redirect to dashboard if already authenticated
 */
function redirectIfAuthenticated() {
    if (isAuthenticated()) {
        window.location.href = 'dashboard.html';
    }
}

/**
 * Logout user
 */
function logout() {
    removeToken();
    window.location.href = 'login.html';
}

/**
 * Make authenticated API request
 */
async function apiRequest(url, options = {}) {
    const token = getToken();
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };
    
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    
    const response = await fetch(`${API_BASE_URL}${url}`, {
        ...options,
        headers
    });
    
    if (response.status === 401) {
        // Unauthorized - token expired or invalid
        logout();
        throw new Error('Session expired. Please login again.');
    }
    
    return response;
}

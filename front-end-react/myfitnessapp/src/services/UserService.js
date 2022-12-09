class UserService {
  getUser(username, jwt) {
    return fetch(`http://localhost:8080/users/${username}`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
      method: "GET",
    });
  }

  addToFav(jwt, addToFavDto) {
    return fetch(`http://localhost:8080/exercise/addToFav`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
      method: "POST",
      body: JSON.stringify(addToFavDto),
    });
  }
}

export default new UserService();

import { exercise_api_key, youtube_api_key } from "../secret";

export const exerciseOptions = {
  method: "GET",
  headers: {
    "X-RapidAPI-Key": exercise_api_key,
    "X-RapidAPI-Host": "exercisedb.p.rapidapi.com",
  },
};

export const youtubeSearchOptions = {
  method: "GET",
  headers: {
    "X-RapidAPI-Key": youtube_api_key,
    "X-RapidAPI-Host": "youtube-search-and-download.p.rapidapi.com",
  },
};

export const fetchData = async (url, options) => {
  const response = await fetch(url, options);
  const data = await response.json();

  return data;
};

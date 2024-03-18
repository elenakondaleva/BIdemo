import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL;

class DataCubeService {
    async getData() {
        try {
            const response = await axios.get(`${BASE_URL}/v1/datacube/data`);
            return response;
        } catch (error) {
            throw error;
        }
    }
}

export default DataCubeService;

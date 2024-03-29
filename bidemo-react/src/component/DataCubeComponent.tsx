import React, {Component} from "react";
import DataCubeService from "../service/DataCubeService";
import DimensionService from "../service/DimensionService";
import {DataCube} from "../model/Dimension"
import Table from "../util/DataCubeUtil";

interface DataCubeComponentProps {}

interface DataCubeComponentState {
    datacube: DataCube[];
    columns: any[];
    rows: any[];
}

class DataCubeComponent extends Component<DataCubeComponentProps, DataCubeComponentState> {
    constructor(props: DataCubeComponentProps) {
        super(props)

        this.state = {
            datacube: [],
            columns: [],
            rows: [],
        }
    }

    componentDidMount() {
        const datacubeServiceInstance = new DataCubeService();
        const dimensionService = new DimensionService();
        datacubeServiceInstance.getData().then((result) => {
            this.setState({datacube: result.data});
        });

        dimensionService.getDimension(0)
            .then(response => {
                const columns = dimensionService.transformData(response.data);
                this.setState({ columns });
            })
            .catch(error => {
                console.error('Error fetching dimensions for position 0:', error);
            });
        dimensionService.getDimension(1)
            .then(response => {
                const rows = dimensionService.transformData(response.data);
                this.setState({ rows });
            })
            .catch(error => {
                console.error('Error fetching dimensions for position 1:', error);
            });

    }

    render() {
        const { datacube, columns, rows } = this.state;

        return (
            <div>
                <h2>Test Task JEDOX</h2>
            <Table data={datacube} columns={columns} rows={rows}/>
            </div>
        );
    }
}

export default DataCubeComponent;
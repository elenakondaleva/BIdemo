import React, { Component } from 'react';
import DimensionService from '../service/DimensionService';
import { Dimension } from '../model/Types';

interface DimensionComponentProps {}

interface DimensionComponentState {
    dimension: Dimension[];
}

class DimensionComponent extends Component<DimensionComponentProps, DimensionComponentState> {
    constructor(props: DimensionComponentProps) {
        super(props);

        this.state = {
            dimension: [],
        };
    }

    componentDidMount() {
        const dimensionServiceInstance = new DimensionService();
        dimensionServiceInstance.getDimension(0).then((result) => {
            this.setState({ dimension: result.data }); // Assuming the result contains the dimension data
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Business Intelligence</h2>
                <h2 className="text-center">Dimension View</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Dimension Id</th>
                            <th>Dimension Name</th>
                            <th>Parent Id</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dimension.map((item) => (
                            <tr key={item.dimensionId}>
                                <td>{item.dimensionId}</td>
                                <td>{item.dimensionName}</td>
                                <td>{item.parentId}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default DimensionComponent;

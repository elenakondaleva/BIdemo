import axios from "axios";
import {Dimension, DimensionHierarchy} from "../model/Types";

const BASE_URL = process.env.REACT_APP_API_BASE_URL;

class DimensionService {
    async getDimension(position: number) {
        try {
            const response = await axios.get( `${BASE_URL}/v1/dimension/${position}/-1/hierarchy`);
            return response;
        } catch (error) {
            throw error;
        }
    }

    transformData(dimensionHier: Dimension[]): DimensionHierarchy[] {
        const hierarchy: DimensionHierarchy[] = [];

        const topLevelDimensions = dimensionHier.filter(dimension => dimension.parentId === null);
        if (!topLevelDimensions?.length) return hierarchy;

        topLevelDimensions.forEach(dimension => {
            const dimensionNode: DimensionHierarchy = {
                dimensionId: dimension.dimensionId,
                dimensionName: dimension.dimensionName,
                level: 0
            };
            const childDimensions = dimensionHier.some(child => child.parentId === dimension.dimensionId);
            if (childDimensions) {
                dimensionNode.children = this.buildChildrenHierarchy(dimension.dimensionId, dimensionHier, 1);
            }

            hierarchy.push(dimensionNode);
        });

        return hierarchy;
    }

    private buildChildrenHierarchy(parentId: number, dimensionHier: Dimension[], level: number): DimensionHierarchy[] {
        const children: DimensionHierarchy[] = [];

        const childDimensions = dimensionHier.filter(dimension => dimension.parentId === parentId);
        if (!childDimensions?.length) return children;

        childDimensions.forEach(dimension => {
            const dimensionNode: DimensionHierarchy = {
                dimensionId: dimension.dimensionId,
                dimensionName: dimension.dimensionName,
                level: level
            };

            const hasChildren = dimensionHier.some(child => child.parentId === dimension.dimensionId);
            if (hasChildren) {
                dimensionNode.children = this.buildChildrenHierarchy(dimension.dimensionId, dimensionHier, level + 1);
            }

            children.push(dimensionNode);
        });

        return children;
    }
}

export default DimensionService;
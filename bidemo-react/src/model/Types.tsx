export interface Dimension {
    dimensionId: number;
    dimensionName: string;
    parentId: number | null;
}

export interface DimensionHierarchy {
    dimensionId: number;
    dimensionName: string;
    level: number;
    children?: DimensionHierarchy[];
}

export interface DataCube {
    dimensionOneId: number;
    dimensionTwoId: number;
    dimensionThreeId: number;
    rawData: number;
}

export interface TableProps {
    data: DataCube[];
    columns: DimensionHierarchy[];
    rows: DimensionHierarchy[];
}

export interface DataCubeComponentProps {}

export interface DataCubeComponentState {
    datacube: DataCube[];
    columns: any[];
    rows: any[];
}
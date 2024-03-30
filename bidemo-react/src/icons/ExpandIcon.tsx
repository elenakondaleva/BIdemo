import React from "react";

interface ExpandIconAttributes {
    className?: string,
}

export const ExpandIcon: React.FC<ExpandIconAttributes> = ({ className = '' }: ExpandIconAttributes) => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" className="icon" fill="none" width="16" viewBox="0 0 24 24">
            <path fill="currentColor" d="M19.5 8.25l-7.5 7.5-7.5-7.5"/>
        </svg>
    );
}
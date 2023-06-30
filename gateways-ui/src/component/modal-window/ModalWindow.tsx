import { PropsWithChildren, useCallback, useState } from "react";
import styles from "./ModalWindow.module.scss";

export interface ModalWindowProps {
  onBackgroundClick?: (e?: MouseEvent) => void;
}

const ModalWindow: React.FC<PropsWithChildren<ModalWindowProps>> = ({
  children,
  onBackgroundClick,
}) => {
  // state
  const [showWindow, setShowWindow] = useState<Boolean>(false);
  // elements
  // handlers
  const backgroundClickHandler = useCallback(
    (e: any) => {
      const event = e as MouseEvent;
      if (event.currentTarget === event.target && onBackgroundClick) {
        onBackgroundClick(e as MouseEvent);
      }
    },
    [onBackgroundClick]
  );

  return (
    <div className={styles.background} onClick={backgroundClickHandler}>
      <div className={styles.modalWindow}>{children}</div>
    </div>
  );
};

export default ModalWindow;
